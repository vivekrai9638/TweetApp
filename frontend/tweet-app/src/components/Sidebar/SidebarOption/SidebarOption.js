import React from 'react'
import './SidebarOption.css'

const SidebarOption = ({active, text, Icon, onClick}) => {
  return (
    <div className={`sidebarOption ${active?'sidebarOption__active':''}`} onClick={onClick}>
        <Icon/>
        <h2>{text}</h2>
    </div>
  )
}

export default SidebarOption