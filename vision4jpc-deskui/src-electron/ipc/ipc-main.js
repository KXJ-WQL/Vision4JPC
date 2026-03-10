import { ipcMain } from 'electron'

const closeWin = (callback)=>{
  ipcMain.on("closeWin",(e)=>{
    callback()
  })
}

export {
  closeWin,
}
