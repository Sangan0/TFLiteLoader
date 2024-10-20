package com.mozhimen.tfloader.imageclassifier.tflitelabel

import com.mozhimen.tfloader.mos.ChipType
import org.tensorflow.lite.support.common.TensorOperator
import org.tensorflow.lite.support.common.ops.NormalizeOp


/**
 * @ClassName TFLiteLoaderQuantizedMobileNet
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/6/17 19:27
 * @Version 1.0
 */
class TFLiteLabelImageClassifierQuantizedMobileNet(
    modelPath: String,
    labelPath: String,
    resultSize: Int,
    chipType: ChipType,
    numThreads: Int
) : TFLiteLabelImageClassifier(modelPath, labelPath, resultSize, chipType, numThreads) {
    /**
     * The quantized model does not require normalization, thus set mean as 0.0f, and std as 1.0f to
     * bypass the normalization.
     */
    private val IMAGE_MEAN = 0.0f

    private val IMAGE_STD = 1.0f

    /** Quantized MobileNet requires additional dequantization to the output probability.  */
    private val PROBABILITY_MEAN = 0.0f

    private val PROBABILITY_STD = 255.0f

    override fun getPreProcessNormalizeOp(): TensorOperator {
        return NormalizeOp(
            IMAGE_MEAN,
            IMAGE_STD
        )
    }

    override fun getPostProcessNormalizeOp(): TensorOperator {
        return NormalizeOp(
            PROBABILITY_MEAN,
            PROBABILITY_STD
        )
    }

}