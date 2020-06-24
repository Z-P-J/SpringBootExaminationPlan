import request from '@/utils/request'

// export function listRoleWithPermission(params) {
//   return request({
//     url: '/role/permission',
//     method: 'get',
//     params
//   })
// }

export function listCourse(params) {
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

export function addCourse(courseForm) {
  return request({
    url: '/course',
    method: 'post',
    data: courseForm
  })
}

export function updateCourse(courseForm) {
  return request({
    url: '/course',
    method: 'put',
    data: courseForm
  })
}

export function removeCourse(courseId) {
  return request({
    url: '/course/' + courseId,
    method: 'delete'
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
