document.addEventListener("DOMContentLoaded", () => {
    const sidebar = document.querySelector(".sidebar");
    const sidebarToggler = document.querySelector(".sidebar-toggler");
    const sidebarToggler2 = document.querySelector(".sidebar-toggler2");
    const content = document.querySelector(".content");

    const fileInput = document.getElementById('profile-upload');
    const previewImage = document.getElementById('preview-image');
    const placeholder = document.querySelector('.profile-img-placeholder');

    const windowResize = () => {
        if (window.innerWidth > 760) {
            sidebar.style.display = "flex";
            sidebarToggler2.style.display = "none";
            sidebarToggler.style.display = "none";
            content.style.marginLeft = "250px";
        } else {
            sidebar.style.display = "none";
            sidebarToggler2.style.display = "block";
            sidebarToggler.style.display = "block";
            content.style.marginLeft = "60px";
        }
    };
    windowResize();
    window.addEventListener("resize", windowResize);

    const contacts = document.querySelectorAll(".contact");
    const searchInput = document.querySelector("#search");
    const searchResult = document.querySelector(".search-result");

    if (searchInput && searchResult) {
        searchInput.addEventListener("keyup", () => {
            const query = searchInput.value.toLowerCase().trim();
            searchResult.innerHTML = '';

            if (query !== '') {
                contacts.forEach(contact => {
                    const text = contact.innerText;
                    const id = contact.firstElementChild.children[0].defaultValue;

                    if (text.toLowerCase().includes(query)) {
                        const element = document.createElement("a");
                        element.innerText = text;
                        element.id = id;
                        element.href = new URL(`/user/contacts/${encodeURIComponent(id)}`, window.location.origin).href;
                        searchResult.appendChild(element);
                    }
                });
            }
        });
    }

    document.addEventListener("click", (evt) => {
        const button = evt.target.closest("button");
        if (button) main(button);
    });

    const main = (button) => {
        switch (button.name) {
            case "menu-close":
                handleMenuClose();
                break;
            case "menu-open":
                handleMenuOpen();
                break;

        }
    };



    const handleMenuOpen = () => {
        sidebar.style.display = "flex";
        sidebarToggler2.style.display = "block";
        sidebarToggler.style.display = "none";
        content.style.marginLeft = "60px";
    };

    const handleMenuClose = () => {
        sidebar.style.display = "none";
        sidebarToggler2.style.display = "none";
        sidebarToggler.style.display = "block";
        content.style.marginLeft = "60px";
    };

    if (fileInput && previewImage && placeholder) {
        fileInput.addEventListener('change', (e) => {
            const file = e.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (e) => {
                    previewImage.src = e.target.result;
                    previewImage.style.display = 'block';
                    placeholder.style.backgroundColor = 'transparent';
                };
                reader.readAsDataURL(file);
            } else {
                previewImage.src = '';
                previewImage.style.display = 'none';
                placeholder.style.backgroundColor = '#f0f0f0';
            }
        });
    }
});