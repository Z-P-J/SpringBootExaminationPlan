import request from '@/utils/request'
// 考次
export function list(params) {
  return request({
    url: '/exam-set',
    method: 'get',
    params
  })
}

export function add(Form) {
  return request({
    url: '/exam-set',
    method: 'post',
    data: Form
  })
}

export function remove(Id) {
  return request({
    url: '/exam-set/' + Id,
    method: 'delete'
  })
}

export function search(Form) {
  return request({
    url: '/exam-set/search',
    method: 'post',
    data: Form
  })
}

export function update(Form) {
  return request({
    url: '/exam-set/' + Form.exams_id,
    method: 'put',
    data: Form
  })
}

export function removeByBatch(Ids) {
  return request({
    url: '/exam-set/batch/' + Ids,
    method: 'delete'
  })
}

export function updateByBatch(Form, Ids) {
  return request({
    url: '/exam-set/batch/' + Ids,
    method: 'put',
    data: Form
  })
}
// 统考课表
export function courseList(params) {
  return request({
    url: '/exam-set/course',
    method: 'get',
    params
  })
}

export function courseAdd(Form) {
  return request({
    url: '/exam-set/course',
    method: 'post',
    data: Form
  })
}

export function courseAddByBatch(Form) {
  return request({
    url: '/exam-set/coursebatch',
    method: 'post',
    data: Form
  })
}

export function courseRemove(Id) {
  return request({
    url: '/exam-set/course/' + Id,
    method: 'delete'
  })
}

export function courseSearch(Form) {
  return request({
    url: '/exam-set/course/search',
    method: 'post',
    data: Form
  })
}

export function courseUpdate(Form) {
  return request({
    url: '/exam-set/course/' + Form.id,
    method: 'put',
    data: Form
  })
}
