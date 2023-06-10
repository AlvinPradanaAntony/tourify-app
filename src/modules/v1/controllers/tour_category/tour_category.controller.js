import { v4 } from 'uuid'
import { TourCategory } from './../../../../models'
import { validateForm } from '../../utils/helper'

export const getAllTourCategory = async (req, res) => {
    try {
        const tourCategories = await TourCategory.findAll()
        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            data: tourCategories
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const storeTourCategory = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        const tourCategory = await TourCategory.create({
            id: v4(),
            name: req.body.name
        })

        res.status(200).json({
            status: 'status',
            message: 'Successfully to creating data',
            data: tourCategory
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const showTourCategory = async (req, res) => {
    try {
        const tourCategory = await TourCategory.findByPk(req.params.id)

        res.status(200).json({
            status: 'success',
            message: 'Successfully getting data',
            message: tourCategory
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const updateTourCategory = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        await TourCategory.update({
            name: req.body.name
        }, {
            where: {
                id: req.params.id
            }
        })

        res.status(200).json({
            status: 'success',
            message: 'Data has been updated',
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const deleteTourCategory = async (req, res) => {
    try {
        await TourCategory.destroy({
            where: { id: req.params.id }
        })

        return res.status(200).json({
            status: 'success',
            message: 'Successfully deleting data'
        })
    } catch(err) {
        return res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}