import axios from 'axios'

export default {
  post: (url, data, config) => {
    return new Promise((resolve, reject) => {
      axios.post(url, data, Object.assign({headers: {}, emulateJSON: true}, config))
        .then((response) => {
          if (response.status === 200) {
            return resolve(response.data)
          } else {
            return reject(response.data.msg)
          }
        }).catch(e => {
        return reject(e)
      })
    })
  },
  get: (url, config) => {
    return new Promise((resolve, reject) => {
      axios.get(url, Object.assign({headers: {}, emulateJSON: true}, config)).then((response) => {
        if (response.status === 200) {
          return resolve(response.data)
        } else {
          return reject(response.data.msg)
        }
      }).catch(e => {
        return reject(e)
      })
    })
  },
  put: (url, params, config) => {
    return new Promise((resolve, reject) => {
      axios.put(url, {}, Object.assign({headers: {}, emulateJSON: true}, config, {params: params})).then((response) => {
        if (response.status === 200) {
          return resolve(response.data)
        } else {
          return reject(response.data.msg)
        }
      }).catch(e => {
        return reject(e)
      })
    })
  },
  delete: (url, config) => {
    return new Promise((resolve, reject) => {
      axios.delete(url, Object.assign({headers: {}, emulateJSON: true}, config)).then((response) => {
        if (response.status === 200) {
          return resolve(response.data)
        } else {
          return reject(response.data.msg)
        }
      }).catch(e => {
        return reject(e)
      })
    })
  }
}
