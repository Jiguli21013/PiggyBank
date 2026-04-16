package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.SaveProductVersionIfChangedResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.SaveProductVersionIfChangedUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import javax.inject.Inject

class SaveProductVersionIfChangedUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val logger: Logger,
) : SaveProductVersionIfChangedUseCase {

    override suspend fun invoke(
        params: SaveProductVersionIfChangedUseCase.Params,
    ): RequestResult<SaveProductVersionIfChangedResult> {
        logger.d(
            LOG_TAG,
            "Start save barcode=${params.barcode}, name=${params.productName}, weight=${params.weightGrams}, price=${params.price}"
        )

        return try {
            val existingProduct = productRepository
                .getProductByBarcode(barcode = params.barcode)
                .getOrThrow()

            logger.d(LOG_TAG, "Existing product found=${existingProduct != null}")

            val result = if (existingProduct == null) {
                createNewProductWithInitialVersion(params = params)
            } else {
                updateExistingProduct(
                    existingProduct = existingProduct,
                    params = params,
                )
            }

            logger.d(LOG_TAG, "Completed successfully: $result")
            Result.success(result).toRequestResult()
        } catch (throwable: Throwable) {
            logger.e(LOG_TAG, "Failed: ${throwable.message}")
            Result.failure<SaveProductVersionIfChangedResult>(throwable).toRequestResult()
        }
    }

    private suspend fun createNewProductWithInitialVersion(
        params: SaveProductVersionIfChangedUseCase.Params,
    ): SaveProductVersionIfChangedResult {
        logger.d(LOG_TAG, "Creating new product with initial version")

        val productId = productRepository
            .createProduct(
                product = Product(
                    id = 0,
                    barcode = params.barcode,
                    productName = params.productName,
                    createdAt = params.changedAt,
                )
            )
            .getOrThrow()

        logger.d(LOG_TAG, "Product created id=$productId")

        val versionId = createCurrentVersion(
            productId = productId,
            params = params,
        )

        logger.d(LOG_TAG, "Initial version created id=$versionId")

        return SaveProductVersionIfChangedResult.NewProductCreated(
            productId = productId,
            versionId = versionId,
        )
    }

    private suspend fun updateExistingProduct(
        existingProduct: Product,
        params: SaveProductVersionIfChangedUseCase.Params,
    ): SaveProductVersionIfChangedResult {
        val currentVersion = productRepository
            .getCurrentVersion(productId = existingProduct.id)
            .getOrThrow()

        logger.d(LOG_TAG, "Current version found=${currentVersion != null}")

        if (currentVersion == null) {
            logger.d(
                LOG_TAG,
                "Existing product has no current version. Creating initial version for productId=${existingProduct.id}"
            )

            updateNameIfNeeded(
                existingProduct = existingProduct,
                newName = params.productName,
            )

            val versionId = createCurrentVersion(
                productId = existingProduct.id,
                params = params,
            )

            logger.d(LOG_TAG, "Initial version created id=$versionId for existing product")

            return SaveProductVersionIfChangedResult.NewProductCreated(
                productId = existingProduct.id,
                versionId = versionId,
            )
        }

        val changes = detectChanges(
            existingProduct = existingProduct,
            currentVersion = currentVersion,
            params = params,
        )

        logger.d(
            LOG_TAG,
            "Detected changes: nameChanged=${changes.nameChanged}, priceChanged=${changes.priceChanged}, weightChanged=${changes.weightChanged}"
        )

        if (!changes.hasAnyChanges) {
            logger.d(LOG_TAG, "No changes detected. Skip saving")
            return SaveProductVersionIfChangedResult.NoChanges(
                productId = existingProduct.id,
                currentVersionId = currentVersion.id,
            )
        }

        updateNameIfNeeded(
            existingProduct = existingProduct,
            newName = params.productName,
        )

        if (!changes.hasVersionChanges) {
            logger.d(LOG_TAG, "Only product name changed. Skip creating new version")
            return SaveProductVersionIfChangedResult.NoChanges(
                productId = existingProduct.id,
                currentVersionId = currentVersion.id,
            )
        }

        return createNewVersion(
            existingProduct = existingProduct,
            currentVersion = currentVersion,
            params = params,
        )
    }

    private fun detectChanges(
        existingProduct: Product,
        currentVersion: ProductVersion,
        params: SaveProductVersionIfChangedUseCase.Params,
    ): ProductChanges {
        return ProductChanges(
            nameChanged = existingProduct.productName != params.productName,
            priceChanged = currentVersion.price != params.price,
            weightChanged = currentVersion.weightGrams != params.weightGrams,
        )
    }

    private suspend fun updateNameIfNeeded(
        existingProduct: Product,
        newName: String,
    ) {
        if (existingProduct.productName == newName) return

        logger.d(LOG_TAG, "Updating product name for productId=${existingProduct.id}")

        productRepository
            .updateProductName(
                productId = existingProduct.id,
                productName = newName,
            )
            .getOrThrow()
    }

    private suspend fun createNewVersion(
        existingProduct: Product,
        currentVersion: ProductVersion,
        params: SaveProductVersionIfChangedUseCase.Params,
    ): SaveProductVersionIfChangedResult.NewVersionCreated {
        logger.d(LOG_TAG, "Creating new version for productId=${existingProduct.id}")

        productRepository
            .clearCurrentVersion(productId = existingProduct.id)
            .getOrThrow()

        val newVersionId = createCurrentVersion(
            productId = existingProduct.id,
            params = params,
        )

        logger.d(LOG_TAG, "New version created id=$newVersionId")

        return SaveProductVersionIfChangedResult.NewVersionCreated(
            productId = existingProduct.id,
            previousVersionId = currentVersion.id,
            newVersionId = newVersionId,
        )
    }

    private suspend fun createCurrentVersion(
        productId: Long,
        params: SaveProductVersionIfChangedUseCase.Params,
    ): Long {
        return productRepository
            .createProductVersion(
                version = ProductVersion(
                    id = 0,
                    productId = productId,
                    weightGrams = params.weightGrams,
                    price = params.price,
                    pricePerKg = params.pricePerKg,
                    createdAt = params.changedAt,
                    isCurrent = true,
                )
            )
            .getOrThrow()
    }

    private companion object {
        const val LOG_TAG = "SaveProductVersionUC"
    }

    private data class ProductChanges(
        val nameChanged: Boolean,
        val priceChanged: Boolean,
        val weightChanged: Boolean,
    ) {
        val hasAnyChanges: Boolean
            get() = nameChanged || priceChanged || weightChanged

        val hasVersionChanges: Boolean
            get() = priceChanged || weightChanged
    }
}
