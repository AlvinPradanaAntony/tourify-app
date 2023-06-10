import { v4 } from "uuid"
import { compare, genSalt, hash } from 'bcrypt'
import { User } from './../../../../models'
import { validateForm } from "../../utils/helper"

export const getAllUsers = async (req, res) => {
    try {
        const user = await User.findAll()
        res.json({
            status: 'success',
            message: 'Successfully getting data',
            data: user
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const createUsers = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        const { name, username, email, password, confirmPassword, phone, address, role } = req.body

        if(password != confirmPassword) {
            res.status(422).json({
                status: 'fail',
                message: 'Password and Confirm Password doesn\'\'t match'
            })
        }

        let hashPassword = await hash(password, (await genSalt(10)).toString())
        const user = await User.create({
            id: v4(),
            name: name,
            email: email,
            username: username,
            password: hashPassword,
            phone: phone,
            address: address,
            role: role
        })

        res.json({
            status: 'success',
            message: 'Successfully to creating user.',
            data: user
        })
    } catch(err) {
        res.json({
            status: 'fail',
            message: err.message
        })
    }
}

export const showUsers = async (req, res) => {
    try {
        const user = await User.findByPk(req.params.id)

        res.json({
            status: 'success',
            message: 'Successfully getting data',
            data: user
        })
    } catch(err) {
        res.status(500).json({
            status: 'fail',
            message: err.message
        })
    }
}

export const updateUsers = async (req, res) => {
    try {
        if(!validateForm(req, res)) return
        const { name, username, email, phone, address, role } = req.body
        await User.update({
            name: name,
            email: email,
            username: username,
            phone: phone,
            address: address,
            role: role
        }, {
            where: { id: req.params.id }
        })

        res.json({
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

export const deleteUsers = async (req, res) => {
    try {
        await User.destroy({
            where: { id: req.params.id }
        })

        res.json({
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