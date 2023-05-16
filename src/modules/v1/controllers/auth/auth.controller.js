import { compare } from 'bcrypt'
import { sign, decode } from 'jsonwebtoken'
import { User } from './../../../../models'

const generateJwtToken = async user => {
    const jwtTokenPayload = {
        id: user.id,
        name: user.name,
        role: user.role,
        phone: user.phone
    }

    return sign(jwtTokenPayload, process.env.JWT_KEY, {
        expiresIn: process.env.JWT_EXPIRES_IN || '60d'
    })
}

export const verifyToken = async (req, res) => {
    return res.json({
        status: 'success',
        message: 'Token verified successfully'
    })
}

export const login = async (req, res) => {
    const { username, password } = req.body

    if(!username || !password) {
        return res.status(422).json({
            status: 'fail',
            message: 'Username & Password required'
        })
    }

    const user = await User.findOne({
        where: { username }
    })

    if(user != null && (await compare(password, user.password))) {
        const token = await generateJwtToken(user);
        res.json({
            status: 'success',
            message: 'Login success',
            data: { token }
        })
    } else {
        res.status(400).json({
            status: 'fail',
            message: 'Wrong username / password'
        })
    }
}

export const refreshToken = async (req, res) => {
    const { exp: expiredTime, id: userId } = decode(
        req.headers.authorization.split(' ')[1]
    )

    if(Date.now() >= expiredTime * 1000) {
        return res.status(403).json({
            status: 'fail',
            message: 'Token is expired'
        })
    }

    const user = await User.findByPk(userId)
    const newJwtToken = await generateJwtToken(user)

    return res.json({
        status: 'success',
        message: 'New token han been issued',
        data: { token: newJwtToken }
    })
}