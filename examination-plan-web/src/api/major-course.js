import request from '@/utils/request'

export function listCourse(params) {
  return request({
    url: '/major-course',
    method: 'get',
    params
  })
}

export function addCourse(courseForm) {
  return request({
    url: '/major-course',
    method: 'post',
    data: courseForm
  })
}

export function updateCourse(courseForm) {
  return request({
    url: '/major-course',
    method: 'put',
    data: courseForm
  })
}

export function removeCourse(courseId) {
  return request({
    url: '/major-course/' + courseId,
    method: 'delete'
  })
}

export function disableCourse(params) {
  return request({
    url: '/major-course/disable',
    method: 'post',
    data: params
  })
}

export function enableCourse(params) {
  return request({
    url: '/major-course/enable',
    method: 'post',
    data: params
  })
}

export function deleteCourse(params) {
  return request({
    url: '/major-course/delete',
    method: 'post',
    data: params
  })
}

export function listDirection(params) {
  return request({
    url: '/major-course/direction',
    method: 'get',
    params
  })
}

export function addDirection(courseForm) {
  return request({
    url: '/major-course/direction',
    method: 'post',
    data: courseForm
  })
}

export function updateDirection(courseForm) {
  return request({
    url: '/major-course/direction',
    method: 'put',
    data: courseForm
  })
}

export function removeDirection(courseId) {
  return request({
    url: '/major-course/direction/' + courseId,
    method: 'delete'
  })
}
