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
    const arr = [];
    arr.length = 0;
    return {
      ...state,
      user_id: null,
      user_name: null,
      cart: arr,
    };
  }
  if (action.type === "SET_BUDGET") {
    const budget = action.payload;
    return {
      ...state,
      budget: budget,
    };
  }
  if (action.type === "RENT_MOVIE") {
    const newBudget = state.budget - action.payload.price;
    if (newBudget < 0) {
      return state;
    } else {
      const cart = state.cart.filter((item) => item.id !== action.payload.id);
      return {
        ...state,
        budget: newBudget,
        cart: cart,
      };
    }
  }
  return state;
};
