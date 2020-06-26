import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/approve',
    method: 'get',
    params
  })
}

export function remove(approveId) {
  return request({
    url: '/approve/' + approveId,
    method: 'delete'
  })
}

export function add(tmpApprove) {
  return request({
    url: '/approve',
    method: 'post',
    data: tmpApprove
  })
}

export function addCheck(tmpApprove) {
  return request({
    url: '/approve/addCheck',
    method: 'post',
    data: tmpApprove
  })
}
// export function search(searchForm) {
//   return request({
//     url: '/approve/search',
//     method: 'post',
//     data: searchForm
//   })
// }
// export function updateApprove(approveForm) {
//   return request({
//     url: '/account/' + approveForm.Id,
//     method: 'put',
//     data: approveForm
//   })
// }

// export function update(approveForm) {
//   return request({
//     url: '/approve',
//     method: 'put',
//     data: approveForm
//   })
// }

