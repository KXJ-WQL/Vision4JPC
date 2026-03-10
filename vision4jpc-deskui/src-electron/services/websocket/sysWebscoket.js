import WebSocket from "ws";

//使用es6新新特性class类
class WebSocketService {
  // 构造函数，接受 WebSocket 连接的 URL 和心跳间隔（默认为3秒）
  constructor(url, heartbeatInterval = 3000) {
    this.url = url; // WebSocket 连接地址
    this.ws = null; // WebSocket 对象
    this.heartbeatInterval = heartbeatInterval; // 心跳间隔时间
    this.heartbeatTimer = null; // 心跳定时器，用于定时发送心跳消息
    this.reconnectTimer = null; // 重连定时器
    this.onMessage = null; // 保存传入的消息处理回调函数
  }

  // 初始化 WebSocket 连接
  init(onMessage) {
    this.onMessage = onMessage; // 设置外部传入的消息处理函数
    this.ws = new WebSocket(this.url); // 创建新的 WebSocket 连接

    // 连接成功时触发
    this.ws.onopen = () => {
      // console.log('WebSocket connected');

      this.startHeartbeat(); // 开始发送心跳
      this.resetReconnectDelay(); // 停止重连尝试
    };

    // 收到消息时触发(?.为可选链接操作符，只要前面为true时才执行后面操作)
    this.ws.onmessage = (message) => this.onMessage?.(JSON.parse(message.data)); // 调用外部传入的回调函数来处理消息

    // WebSocket 错误处理
    this.ws.onerror = (error) => {
      // console.error('WebSocket error:', error);
    };

    // WebSocket 连接关闭时触发
    this.ws.onclose = () => {
      // console.log('WebSocket closed --- wql');
      this.stopHeartbeat(); // 停止心跳
      this.reconnect(); // 尝试重新连接
    };
  }

  // 开始心跳（定时发送心跳消息，保持连接活跃）
  startHeartbeat() {
    this.heartbeatTimer = setInterval(() => {
      if (this.ws?.readyState === WebSocket.OPEN) {
        // 如果 WebSocket 连接已打开，发送心跳消息
        this.send(JSON.stringify({ type: "99" })); // 心跳消息为类型为 'ping' 的 JSON 字符串
      }
    }, this.heartbeatInterval); // 按指定的心跳间隔发送心跳消息
  }

  // 停止心跳
  stopHeartbeat() {
    clearInterval(this.heartbeatTimer); // 清除定时器
    this.heartbeatTimer = null; // 重置定时器对象
  }

  // 发送消息
  send(message) {
    this.ws?.readyState === WebSocket.OPEN && this.ws.send(message); // 如果 WebSocket 处于打开状态，才发送消息
  }

  // 关闭 WebSocket 连接
  close() {
    this.ws?.close(); // 如果 WebSocket 对象存在，关闭连接
  }

  // 尝试重连
  reconnect() {
    // 防止重复创建重连定时器
    if (!this.reconnectTimer) {
      this.reconnectTimer = setInterval(() => {
        console.log(`Attempting to reconnect...`);
        this.init(this.onMessage); // 重新初始化 WebSocket 连接，并使用相同的消息处理函数
      }, 1500); // 每1.5秒尝试重连一次
    }
  }

  // 关闭延迟重连
  resetReconnectDelay() {
    clearInterval(this.reconnectTimer); // 清除重连定时器
    this.reconnectTimer = null; // 重置定时器对象
  }
}

export default WebSocketService;
