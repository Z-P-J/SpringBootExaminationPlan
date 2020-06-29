import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/approve',
    method: 'get',
    params
  })
}

export function remove(approve_id) {
  return request({
    url: '/approve/' + approve_id,
    method: 'delete'
  })
}

export function search(From) {
  return request({
    url: '/approve/search',
    method: 'post',
    data: From
  })
}

export function update(tmpApprove) {
  return request({
    url: '/approve/' + tmpApprove.approve_id,
    method: 'put',
    data: tmpApprove
  })
}

export function add(tmpApprove) {
  return request({
    url: '/approve',
    method: 'post',
    data: tmpApprove
  })
}
// continue
export function continuelist(params, school_id) {
  return request({
    url: '/approve/continue/' + school_id,
    method: 'get',
    params
  })
}

export function extendlist(params, school_id) {
  return request({
    url: '/approve/extend/' + school_id,
    method: 'get',
    params
  })
}

export function addByBatch(Form, Ids) {
  return request({
    url: '/approve/batch/' + Ids,
    method: 'put',
    data: Form
  })
}
