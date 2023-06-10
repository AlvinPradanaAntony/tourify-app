import { v4 } from 'uuid'
import { Rating } from './../../../../models'
import { validateForm } from '../../utils/helper'

export const getAllRating = async (req, res) => {
    try {
        const rating = await Rating.findAll()

        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: rating
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const getRatingWhereUserAndTouristDestination = async (req, res) => {
    try {
        const rating = await Rating.findOne({
            where: {
                tourist_destination_id: req.params.touristDestinationId,
                user_id: req.params.userId,
            }
        })

        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: rating
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const getRatingWhereTouristDestination = async (req, res) => {
    try {
        const rating = await Rating.findAll({
            where: {
                tourist_destination_id: req.params.touristDestinationId
            }
        })

        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: rating
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const storeRating = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        var data = req.body.data.map((val, key) => {
            const { user_id, tourist_destination_id, score, review } = val

            return {
                id: v4(),
                user_id: user_id,
                tourist_destination_id: tourist_destination_id,
                score: score,
                review: review,
                updatedAt: new Date(),
                createdAt: new Date()
            }
        })

        await Rating.bulkCreate(data)
        res.status(200).json({
            status: 'success',
            message: 'Successfully creating data',
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}