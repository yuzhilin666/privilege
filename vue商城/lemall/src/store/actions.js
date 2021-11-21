export default{
  updateUserInfo(context, nickName) {
    context.commit('updateUserInfo', nickName);
  },
  updateCartCount(context, cartCount) {
    context.commit('updateCartCount', cartCount);
  },
  initCartCount(context, cartCount) {
    context.commit('initCartCount', cartCount);
  }
}