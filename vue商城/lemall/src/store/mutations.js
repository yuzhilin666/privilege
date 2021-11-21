export default {
  updateUserInfo(state, nickName) {
    state.nickName = nickName;
  },
  updateCartCount(state, cartCount) {
    state.cartCount += cartCount;
  },
  initCartCount(state, cartCount) {
    state.cartCount = cartCount;
  }
}