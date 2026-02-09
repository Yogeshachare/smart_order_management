import axiosInstance from "."

export const login = (email: string, password: string) => {
    return axiosInstance.post('/auth/login', {email, password})    
}