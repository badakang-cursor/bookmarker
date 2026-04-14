'use client'

import { saveBookmark } from '@/services/api/fetchBookmarks';
import React, { useState } from 'react'


const page = () => {
  const [title, setTitle] = useState('');
  const [url, setUrl] = useState('');
  const [message, setMessage] = useState<String|null>(null);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement> | SubmitEvent) =>{
    e.preventDefault();
 
    // 유효성 검사
    if(!url){
      alert('Link 를 입력해주세요');
    }

    const payload = {
      title,
      url
    }

    try {
      saveBookmark(payload)
        .then(response => {
          console.log("Save Bookmark response", response);
          setTitle("");
          setUrl("");
          setMessage("새로운 Bookmark를 저장했습니다.")
        })
        .catch(error => {
          setMessage("새로운 Bookmark를 저장에 실패했습니다.[1]")
        })
    } catch (error) {
      //Promise 가 reject 된 경우
      setMessage("새로운 Bookmark를 저장에 실패했습니다.[2]")
    }
  }
  return (
    
    <div>
      {message && <div className='alert alert-primary' role='alert'>{message}</div>}
      <form onSubmit={e => handleSubmit(e)}>
        <legend>새로운 Bookmark 등록</legend>
        <div className='mb-3'>
          <label htmlFor='title' className='form-label'>제목</label>
          <input type='text' className='form-control' placeholder='Title' value={title} onChange={e=>setTitle(e.target.value)} />
        </div>
        <div className='mb-3'>
          <label htmlFor='url' className='form-label'>링크</label>
          <input type='text' className='form-control' placeholder='Link' value={url} onChange={e=>setUrl(e.target.value)} />
        </div>
        <div className='d-grid gap-2 col-6 mx-auto'>
          <button type='submit' className='btn btn-primary'>Submit</button>
        </div>
      </form>
    </div>
  )
}

export default page
