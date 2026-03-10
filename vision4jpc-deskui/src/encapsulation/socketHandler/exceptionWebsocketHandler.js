import { errorNotift } from "utils/notiftUtil";

// 使用闭包封装数据的方式来管理这些数据和处理逻辑
export const createTypeHandlers = (handlersMetaData) => {
  return {
    108: (data) => {
      errorNotift(data.data)
    }
  };
};
