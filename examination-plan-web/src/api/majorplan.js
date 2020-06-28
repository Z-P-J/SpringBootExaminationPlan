import request from '@/utils/request'

export function listMajorplan(query) {
  return request({
    url: '/majorplan',
    method: 'get',
    params: query
  })
}

export function addMajorplan(majorplanForm) {
  return request({
    url: '/majorplan',
    method: 'post',
    data: majorplanForm
  })
}

export function updateMajorplan(majorplanForm) {
  return request({
    url: '/majorplan',
    method: 'put',
    data: majorplanForm
  })
}

export function removeMajorplan(majorPlanId) {
  return request({
    url: '/majorplan/' + majorPlanId,
    method: 'delete'
  })
}

export function deleteMajorplan(params) {
  return request({
    url: '/majorplan/delete',
    method: 'post',
    data: params
  })
}

export function disableMajorplan(params) {
  return request({
    url: '/majorplan/disable',
    method: 'post',
    data: params
  })
}

export function enableMajorplan(params) {
  return request({
    url: '/majorplan/enable',
    method: 'post',
    data: params
  })
}
