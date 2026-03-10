/**
 * 设置 WebSocket 监听器，用于处理接收到的消息。
 * @param {Object} ipcApiListener - WebSocket 监听器实例，负责接收 WebSocket 消息。
 * @param {Object} typeHandlers - 包含处理不同类型消息的处理函数的对象。
 */
export const setupWebSocketListener = (ipcApiListener, typeHandlers) => {
  // 注册 WebSocket 消息监听器
  ipcApiListener.websocketMessageListener((data) => {
    // 从 typeHandlers 中获取与接收到的消息类型对应的处理函数
    const handler = typeHandlers[data.type];
    // 如果找到相应的处理函数，则调用它
    if (handler) {
      // 传递 WebSocket 数据给处理函数
      handler(data);
    }
  });
};
