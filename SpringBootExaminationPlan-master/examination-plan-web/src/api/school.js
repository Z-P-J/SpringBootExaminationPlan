import request from '@/utils/request'
// 考次
export function schoolList(params) {
  return request({
    url: '/school',
    method: 'get',
    params
  })
}

export function schoolAdd(Form) {
  return request({
    url: '/school',
    method: 'post',
    data: Form
  })
}

export function schoolRemove(Id) {
  return request({
    url: '/school/' + Id,
    method: 'delete'
  })
}

export function schoolUpdate(Form) {
  return request({
    url: '/school/' + Form.exams_id,
    method: 'put',
    data: Form
  })
}
