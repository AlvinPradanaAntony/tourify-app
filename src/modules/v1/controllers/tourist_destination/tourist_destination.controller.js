import { v4 } from 'uuid'
import { TouristDestination, TourCategory, User } from "./../../../../models"
import { validateForm } from '../../utils/helper'

export const getAllTourDestination = async (req, res) => {
    try {
        const tourDestination = await TouristDestination.findAll({
            include: [
                { model: TourCategory },
                { model: User }
            ]
        })

        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: tourDestination
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const storeTourDestination = async (req, res) => {
    try {
        if(!validateForm(req, res)) return 
        const { tour_category_id, name, phone, email, price, description, address, longitude, latitude, day_open_start, day_open_end, time_open_start, time_open_end, user_id, picture } = req.body
        
        const tourDestination = await TouristDestination.create({
            id: v4(),
            tour_category_id: tour_category_id,
            name: name,
            phone: phone,
            email: email,
            price: price,
            description: description,
            address: address,
            longitude: longitude,
            latitude: latitude,
            day_open_start: day_open_start,
            day_open_end: day_open_end,
            time_open_start: time_open_start,
            time_open_end: time_open_end,
            user_id: user_id,
            picture: picture
        })

        res.status(200).json({
            status: 'success',
            message: 'Successfully creating data',
            data: tourDestination
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const showTourDestination = async (req, res) => {
    try {
        const tourDestination = await TouristDestination.findByPk(req.params.id)
        
        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: tourDestination
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const updateTourDestination = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        const { tour_category_id, name, phone, email, price, description, address, longitude, latitude, day_open_start, day_open_end, time_open_start, time_open_end, user_id, picture } = req.body

        await TouristDestination.update({
            tour_category_id: tour_category_id,
            name: name,
            phone: phone,
            email: email,
            price: price,
            description: description,
            address: address,
            longitude: longitude,
            latitude: latitude,
            day_open_start: day_open_start,
            day_open_end: day_open_end,
            time_open_start: time_open_start,
            time_open_end: time_open_end,
            user_id: user_id,
            picture: picture
        })

        res.status(200).json({
            status: 'success',
            message: 'Data has been updated'
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const deleteTourDestination = async (req, res) => {
    try {
        await TouristDestination.destroy({
            where: { id: req.params.id }
        })

        res.status(200).json({
            status: 'success',
            message: 'Successfully deleting data'
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}