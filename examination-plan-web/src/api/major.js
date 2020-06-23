import request from '@/utils/request'
export function list(params) {
  return request({
    url: '/major',
    method: 'get',
    params
  })
}

export function detail() {
  return request({
    url: '/major/detail',
    method: 'get'
  })
}

export function add(Form) {
  return request({
    url: '/major',
    method: 'post',
    data: Form
  })
}

export function remove(Id) {
  return request({
    url: '/major/' + Id,
    method: 'delete'
  })
}

export function search(Form) {
  return request({
    url: '/major/search',
    method: 'post',
    data: Form
  })
}

export function update(Form) {
  return request({
    url: '/major/' + Form.major_id,
    method: 'put',
    data: Form
  })
}
