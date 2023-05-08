import path from "path"
import dotenv from "dotenv"

export const executeLoader = () => {
    const dotenvConfig = dotenv.config({
        path: path.join(__dirname, '..', '.env')
    })
    return { dotenvConfig }
}