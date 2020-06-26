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
