import 'bootstrap/dist/css/bootstrap.min.css'

function CreateClosets(){
    return(
        <div className="feature-container m-4 p-0 d-flex align-items-center">
            <p className='feature-title fw-bold m-0 p-0 text-center d-flex flex-column justify-content-center'>
                Create your own <br/>
                virtual closets
                <span className='feature-desc m-4'>
                    Set and manage your virtual closets by defining their structure and add your own items or search the app for inspiration! 
                </span>
            </p>
            <div className='feature-image p-0 d-flex'>
                <img src='/assets/images/feature_img2.png' className='feature-illustration'></img>
            </div>
        </div>
    );
}

export default CreateClosets