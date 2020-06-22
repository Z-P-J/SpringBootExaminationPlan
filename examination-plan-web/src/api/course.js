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

export function add(roleForm) {
  return request({
    url: '/course',
    method: 'post',
    data: roleForm
  })
}

export function update(roleForm) {
  return request({
    url: '/course',
    method: 'put',
    data: roleForm
  })
}

export function remove(roleId) {
  return request({
    url: '/course/' + roleId,
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
