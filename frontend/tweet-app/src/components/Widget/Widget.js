import React from 'react'
import './Widget.css';
import SearchIcon from '@mui/icons-material/Search';

const Widget = () => {
  return (
    <div className='widget'>
      <div className="widget__searchBar">
        <SearchIcon/>
        <input type="text" placeholder='Search Twitter'/>
      </div>
    </div>
  )
}

export default Widget