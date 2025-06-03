export const getToken = () => localStorage.getItem('token');
export const getRole = () => localStorage.getItem('role');

export const isAuthenticated = () => !!getToken();

export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
};
