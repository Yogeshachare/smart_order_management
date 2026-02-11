import axiosInstance from "."

export const login = (email: string, password: string) => {
    return axiosInstance.post('/auth/login', {email, password})    
}

export const register = (registerData: {email: string, name: string, password: string}) => {
    return axiosInstance.post('/auth/register', registerData)
}