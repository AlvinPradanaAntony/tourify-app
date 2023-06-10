import { validationResult } from "express-validator";
import util from 'util'
import gc from './../../../config/cloud-storage'
const bucket = gc.bucket('tourify-bucket')

export const uploadImage = (file) => new Promise((resolve, reject) => {
    const { originalname, buffer } = file
  
    const blob = bucket.file(originalname.replace(/ /g, "_"))
    const blobStream = blob.createWriteStream({
        resumable: false
    })
    blobStream.on('finish', () => {
        const publicUrl = `https://storage.googleapis.com/${bucket.name}/${blob.name}`
        resolve(publicUrl)
    })
    .on('error', () => {
        reject(`Unable to upload image, something went wrong`)
    })
    .end(buffer)
})

export const validateForm = (req, res) => {
    const result = validationResult(req)

    if(!result.isEmpty()) {
        res.status(422).json({
            status: 'fail',
            message: 'Validation Errors',
            errors: result.array()
        })

        return false
    }

    return true
}