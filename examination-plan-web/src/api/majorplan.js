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
