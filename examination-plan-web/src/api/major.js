import request from '@/utils/request'
// 专业详细信息
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

// 专业大类
export function categoryList(params) {
  return request({
    url: '/major/category',
    method: 'get',
    params
  })
}

export function categoryAdd(Form) {
  return request({
    url: '/major/category',
    method: 'post',
    data: Form
  })
}

export function categoryRemove(Id) {
  return request({
    url: '/major/category/' + Id,
    method: 'delete'
  })
}

export function categorySearch(Form) {
  return request({
    url: '/major/category/search',
    method: 'post',
    data: Form
  })
}

export function categoryUpdate(Form) {
  return request({
    url: '/major/category/' + Form.major_category_code,
    method: 'put',
    data: Form
  })
}

// 主考院校
export function schoolList(params, Id) {
  return request({
    url: '/major/school/' + Id,
    method: 'get',
    params
  })
}

export function schoolAdd(Form) {
  return request({
    url: '/major/school',
    method: 'post',
    data: Form
  })
}

export function schoolRemove(Id) {
  return request({
    url: '/major/school/' + Id,
    method: 'delete'
  })
}

export function schoolSearch(Form, Id) {
  return request({
    url: '/major/school/search/' + Id,
    method: 'post',
    data: Form
  })
}

export function schoolUpdate(Form) {
  return request({
    url: '/major/school/' + Form.id,
    method: 'put',
    data: Form
  })
}