import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/course',
    method: 'get',
    params
  })
}

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
