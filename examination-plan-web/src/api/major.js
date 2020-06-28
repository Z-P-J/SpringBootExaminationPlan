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

export function removeByBatch(Ids) {
  return request({
    url: '/major/batch/' + Ids,
    method: 'delete'
  })
}

export function updateByBatch(Form, Ids) {
  return request({
    url: '/major/batch/' + Ids,
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

// 专业大类
export function nationList(params) {
  return request({
    url: '/major/nation',
    method: 'get',
    params
  })
}

export function nationAdd(Form) {
  return request({
    url: '/major/nation',
    method: 'post',
    data: Form
  })
}

export function nationRemove(Id) {
  return request({
    url: '/major/nation/' + Id,
    method: 'delete'
  })
}

export function nationSearch(Form) {
  return request({
    url: '/major/nation/search',
    method: 'post',
    data: Form
  })
}

export function nationUpdate(Form) {
  return request({
    url: '/major/nation/' + Form.national_major_code,
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

export function schoolUpdateByBatch(params) {
  return request({
    url: '/major/school/batch',
    method: 'put',
    data: params
  })
}
