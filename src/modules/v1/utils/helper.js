import { validationResult } from "express-validator";

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