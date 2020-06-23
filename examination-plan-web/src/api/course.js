import request from '@/utils/request'

// export function listRoleWithPermission(params) {
//   return request({
//     url: '/role/permission',
//     method: 'get',
//     params
//   })
// }

export function list(params) {
  return request({
    url: '/course',
    method: 'get',
    params
  })
}

// export function listResourcePermission(params) {
//   return request({
//     url: '/permission',
//     method: 'get',
//     params
//   })
// }

export function add(courseForm) {
  return request({
    url: '/course',
    method: 'post',
    data: courseForm
  })
}

export function update(courseForm) {
  return request({
    url: '/course',
    method: 'put',
    data: courseForm
  })
}

export function remove(courseId) {
  return request({
    url: '/course/' + courseId,
    method: 'delete'
  })
}

// export function updateAccountRole(account) {
//   return request({
//     url: '/account/role',
//     method: 'put',
//     data: account
//   })
// }
