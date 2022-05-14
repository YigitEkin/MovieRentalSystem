export const reducer = (state, action) => {
  if (action.type === "SET_USER_ID") {
    const user_id = action.payload;
    return {
      ...state,
      user_id: user_id,
    };
  }
  if (action.type === "SET_USER_NAME") {
    const user_name = action.payload;
    return {
      ...state,
      user_name: user_name,
    };
  }
  if (action.type === "SET_CART") {
    const newItem = action.payload;
    const cart = [...state.cart, newItem];
    return {
      ...state,
      cart: cart,
    };
  }
  if (action.type === "REMOVE_CART") {
    const id = action.payload;
    const cart = state.cart.filter((item) => item.id !== id);
    return {
      ...state,
      cart: cart,
    };
  }
  if (action.type === "LOGOUT") {
    return {
      ...state,
      user_id: null,
      user_name: null,
      cart: [],
    };
  }
  return state;
};
