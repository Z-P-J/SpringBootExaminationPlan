import request from '@/utils/request'

export function listMajorCourse(params) {
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

export function updateMajorCourse(courseForm) {
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
    url: '/course/disable',
    method: 'post',
    data: params
  })
}

export function enableCourse(params) {
  return request({
    url: '/course/enable',
    method: 'post',
    data: params
  })
}

export function deleteCourse(params) {
  return request({
    url: '/course/delete',
    method: 'post',
    data: params
  })
}

export function listTextbook(params) {
  return request({
    url: '/course/textbook',
    method: 'get',
    params
  })
}

export function addTextbook(form) {
  return request({
    url: '/course/textbook',
    method: 'post',
    data: form
  })
}

export function updateTextbook(form) {
  return request({
    url: '/course/textbook',
    method: 'put',
    data: form
  })
}

export function removeTextbook(courseId) {
  return request({
    url: '/course/textbook/' + courseId,
    method: 'delete'
  })
}

export function getTextbook(courseId) {
  return request({
    url: '/course/textbook/' + courseId,
    method: 'get'
  })
}

export function listNationalCourse(params) {
  return request({
    url: '/course/national',
    method: 'get',
    params
  })
}

export function addNationalCourse(form) {
  return request({
    url: '/course/national',
    method: 'post',
    data: form
  })
}

export function updateNationalCourse(form) {
  return request({
    url: '/course/national',
    method: 'put',
    data: form
  })
}

export function removeNationalCourse(courseId) {
  return request({
    url: '/course/national/' + courseId,
    method: 'delete'
  })
}

export function listCourseCharge(params) {
  return request({
    url: '/course/charge',
    method: 'get',
    params
  })
}

export function addCourseCharge(form) {
  return request({
    url: '/course/charge',
    method: 'post',
    data: form
  })
}

export function updateCourseCharge(form) {
  return request({
    url: '/course/charge',
    method: 'put',
    data: form
  })
}

export function removeCourseCharge(courseId) {
  return request({
    url: '/course/charge/' + courseId,
    method: 'delete'
  })
}
