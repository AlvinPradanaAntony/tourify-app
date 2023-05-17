import { body, check } from 'express-validator'
import validationMessage from './../../../validation-messages/message-id.json'

export const loginValidation = [
    body('username').notEmpty().withMessage(validationMessage.required),
    body('password').notEmpty().withMessage(validationMessage.required)
]

export const registerValidation = [
    body('name').notEmpty().withMessage(validationMessage.required),
    body('username').notEmpty().withMessage(validationMessage.required),
    body('email').notEmpty().isEmail().withMessage(validationMessage.required),
    body('password').notEmpty().withMessage(validationMessage.required)
]