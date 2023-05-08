import process from 'node:process'
import express from 'express'
import * as Routes from "./routes"

export const APP_VERSION = 'v1'
const app = express()

for(const route in Routes) {
    Routes[route](app)
}

export default app