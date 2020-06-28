import request from '@/utils/request'

export function listExam(params) {
  return request({
    url: '/exam-plan',
    method: 'get',
    params
  })
}

export function addExam(examForm) {
  return request({
    url: '/exam-plan',
    method: 'post',
    data: examForm
  })
}

export function updateExam(examForm) {
  return request({
    url: '/exam-plan',
    method: 'put',
    data: examForm
  })
}

export function removeExam(examId) {
  return request({
    url: '/exam-plan/' + examId,
    method: 'delete'
  })
}

export function listCourse(params) {
  return request({
    url: '/exam-plan/course',
    method: 'get',
    params
  })
}

export function addCourse(examForm) {
  return request({
    url: '/exam-plan/course',
    method: 'post',
    data: examForm
  })
}

export function updateCourse(examForm) {
  return request({
    url: '/exam-plan/course',
    method: 'put',
    data: examForm
  })
}

export function removeCourse(examId) {
  return request({
    url: '/exam-plan/course/' + examId,
    method: 'delete'
  })
}

export function listApprove(params) {
  return request({
    url: '/exam-plan/approve',
    method: 'get',
    params
  })
}

export function addApprove(examForm) {
  return request({
    url: '/exam-plan/approve',
    method: 'post',
    data: examForm
  })
}

export function updateApprove(examForm) {
  return request({
    url: '/exam-plan/approve',
    method: 'put',
    data: examForm
  })
}

export function removeApprove(examId) {
  return request({
    url: '/exam-plan/approve/' + examId,
    method: 'delete'
  })
}
