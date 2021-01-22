import axios from 'axios'

const instance = axios.create({  
    baseURL: '/api',
    timeout: 1000
})

function saveToken(token) {
    instance.defaults.headers.common['Authorization'] = 'Bearer ' + token
    localStorage.setItem('token', token)
}

function removeToken() {
    instance.defaults.headers.common['Authorization'] = null
    localStorage.removeItem('token')
}

if (localStorage.getItem('token')) {
    instance.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
}

export default {
    isLogged: () => {
        return !!localStorage.getItem('token');
    },
    bid: {
        gets: (page, size, withOwner, withMedia) => instance.get('bid', { params: { page: page | 0, size: size | 10, withOwner: withOwner | 1, withMedia: withMedia | 1 } }),
        get: (id, withOwner, withMedia) => instance.get('bid/' + id, { params: { withOwner: withOwner | 1, withMedia: withMedia | 1 } }),
        create: (model) => instance.post('bid', model),
        update: (id, model) => instance.put('bid/' + id, model),
        delete: (id) => instance.delete('bid/' + id)
    },
    auth: {
        login: (username, password) => {
            return instance.post('auth', { username, password })
                .then((response) => {
                    saveToken(response.data);
                    return true;
                });
        },
        logout: () => {
            removeToken();
        }
    }
}
