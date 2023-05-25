import { Router } from 'express'

// controllers
import { login, register, refreshToken, verifyToken } from '../controllers/auth/auth.controller'
import { getAllUsers, createUsers, showUsers } from '../controllers/user/user.controller'

// validations
import { loginValidation, registerValidation } from '../validations/auth.validation'

import authenticatedMiddleware from '../middlewares/authenticated'
import { body } from 'express-validator'

export const loadAuthRouter = app => {
    const router = Router()
    router.route('/login').post(...loginValidation, login)
    router.route('/register').post(...registerValidation, register)

    router.use(authenticatedMiddleware)
    router.route('/verify').get(verifyToken)
    router.route('/refresh').post(refreshToken)

    app.use('/auth', router)
}

export const loadUserRouter = app => {
    const router = Router()
    router.use(authenticatedMiddleware)
    router.route('/')
            .get(getAllUsers)
            .post(createUsers)
    router.route('/:id')
            .get(showUsers)

    app.use('/users', router)
}