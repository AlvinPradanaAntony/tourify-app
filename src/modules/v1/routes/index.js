import { Router } from 'express'
import { login, register, refreshToken, verifyToken } from '../controllers/auth/auth.controller'
import { loginValidation, registerValidation } from '../validations/auth.validation'
import authenticatedMiddleware from '../middlewares/authenticated'
import { body } from 'express-validator'

export const loadAuthRouter = app => {
    const router = Router()
    router.post('/login', body('username').notEmpty(), login)
    router.route('/register').post(...registerValidation, register)

    router.use(authenticatedMiddleware)
    router.route('/verify').get(verifyToken)
    router.route('/refresh').post(refreshToken)

    app.use('/auth', router)
}