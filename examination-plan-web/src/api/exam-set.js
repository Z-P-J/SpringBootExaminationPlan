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

