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
    path: '/role3',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '专业管理',
    children: [{
      path: 'list',
      name: '专业管理',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  {
    path: '/role4',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '考次管理',
    children: [{
      path: 'list',
      name: '考次管理',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
    }]
  },
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    icon: 'role',
    noDropDown: true,
    // name: '课程管理',
    children: [{
      path: 'list',
      name: '课程管理',
      component: _import('course/list')
      // meta: { permission: ['role:list'] }
    }, {
      path: 'detail',
      name: '课程详情',
      component: _import('course/detail')
      // meta: { permission: ['role:list'] }
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
    path: '/role8',
    component: Layout,
    redirect: '/role/list',
    icon: 'role',
    noDropDown: true,
    // name: '专业申报',
    children: [{
      path: 'list',
      name: '专业申报',
      component: _import('role/list'),
      meta: { permission: ['role:list'] }
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
