import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)

Vue.use(Router)

/**
 * icon : the icon show in the sidebar
 * hidden : if `hidden:true` will not show in the sidebar
 * redirect : if `redirect:noRedirect` will not redirect in the levelBar
 * noDropDown : if `noDropDown:true` will not has submenu in the sidebar
 * meta : `{ permission: ['a:xx'] }`  will control the page permission
 **/
export const constantRouterMap = [
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    icon: 'dashboard',
    noDropDown: true,
    children: [{
      path: 'dashboard',
      name: '控制台',
      component: _import('dashboard/index'),
      meta: { title: 'dashboard', noCache: true }
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/account',
    component: Layout,
    redirect: '/account/list',
    icon: 'name',
    noDropDown: true,
    children: [{
      path: 'list',
      name: '账户管理',
      component: _import('account/list'),
      meta: { permission: ['account:list'] }
    }]
  },

  {
    path: '/account',
    component: Layout,
    redirect: '/account/detail',
    hidden: true,
    children: [{
      path: 'detail',
      name: '账户中心',
      component: _import('account/detail')
    }]
  },

  {
    path: '/role',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    children: [{
      path: 'list',
      name: '角色管理',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  // {
  //   path: '/role1',
  //   component: Layout,
  //   redirect: '/role/list',
  //   icon: 'role',
  //   noDropDown: true,
  //   name: '计划管理',
  //   children: [
  //     {
  //       path: 'list',
  //       name: '专业计划',
  //       component: _import('role/list'),
  //       meta: { permission: ['role:list'] }
  //     },
  //     {
  //       path: 'list',
  //       name: '专业管理',
  //       component: _import('role/list'),
  //       meta: { permission: ['role:list'] }
  //     },
  //     {
  //       path: 'list',
  //       name: '课程管理',
  //       component: _import('role/list'),
  //       meta: { permission: ['role:list'] }
  //     },
  //     {
  //       path: 'list',
  //       name: '专业课程管理',
  //       component: _import('role/list'),
  //       meta: { permission: ['role:list'] }
  //     },
  //     {
  //       path: 'list',
  //       name: '专业计划统计报表',
  //       component: _import('role/list'),
  //       meta: { permission: ['role:list'] }
  //     }
  //   ]
  // },
  {
    path: '/major-plan',
    component: Layout,
    redirect: '/major-plan/list',
    icon: 'icon',
    noDropDown: true,
    // name: '专业计划',
    children: [{
      path: 'list',
      name: '专业计划',
      component: _import('major-plan/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  {
    path: '/major',
    component: Layout,
    redirect: '/major/list',
    icon: 'chart',
    name: '专业管理',
    noDropDown: false,
    children: [{
      path: 'list',
      name: '专业信息维护',
      component: _import('major/list')
    }, {
      hidden: true,
      path: 'detail',
      name: '专业详情',
      component: _import('major/detail')
    },
    {
      path: 'category',
      name: '专业大类',
      component: _import('major/category')
    },
    {
      path: 'nation',
      name: '全国专业',
      component: _import('major/nation')
    },
    {
      hidden: true,
      path: 'school',
      name: '主考院校',
      component: _import('major/school')
    }
    ]
  },
  {
    path: '/exam-set',
    component: Layout,
    redirect: '/exam-set/list',
    icon: 'education',
    noDropDown: false,
    name: '考次管理',
    children: [{
      path: 'list',
      name: '考次管理',
      component: _import('exam-set/list')
    },
    {
      path: 'course',
      name: '统考课表',
      component: _import('exam-set/course')
    }
    ]
  },
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    icon: 'role',
    noDropDown: false,
    name: '课程管理',
    children: [{
      path: 'list',
      name: '全部课程',
      component: _import('course/list')
    }, {
      path: 'national',
      name: '全国课程',
      component: _import('course/national')
    }, {
      path: 'textbook',
      name: '课程教材',
      component: _import('course/textbook')
    }]
  },
  {
    path: '/role6',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '专业课程',
    children: [{
      path: 'list',
      name: '专业课程',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  {
    path: '/role7',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '考试计划',
    children: [{
      path: 'list',
      name: '考试计划',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  {
    path: '/approve',
    component: Layout,
    redirect: '/approve/list',
    icon: 'role',
    noDropDown: true,
    name: '专业申报',
    children: [{
      path: 'list',
      name: '专业申报',
      component: _import('approve/list')
      // meta: { permission: ['role:list'] }
    }, {
      path: 'detail',
      name: '申请表详情',
      component: _import('approve/detail')
    }, {
      path: 'check',
      name: '审核表',
      component: _import('approve/check')
    }, {
      path: 'selectmajor_list',
      name: '专业列表',
      component: _import('approve/selectmajor_list')
    }, {
      path: 'add_new',
      name: '新专业',
      component: _import('approve/add_new')
    }, {
      path: 'selectcourse_list',
      name: '调整专业课程',
      component: _import('approve/selectcourse_list')
    }]
  },
  {
    path: '/role9',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '助学管理',
    children: [{
      path: 'list',
      name: '助学管理',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  }
]
