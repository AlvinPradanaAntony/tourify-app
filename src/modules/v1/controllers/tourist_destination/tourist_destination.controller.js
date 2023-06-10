import * as tf from '@tensorflow/tfjs'

export const test = async (req, res) => {
    const model = await tf.MLTask.NLClassification.TFLiteCustomModel.load({
        modelUrl: 'https://github.com/Bangkit-C2-PS424-Team/tourify-app/blob/6927544f83656429e865d3b8de2d35ccdfe67e13/model(2).tflite'
    })

    const result = await model.classify(req.params.input)
    res.status(200).json({
        status: 'Success',
        message: result
    })
}