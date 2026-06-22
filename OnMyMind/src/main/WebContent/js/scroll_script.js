if ('history' in window && 'scrollRestoration' in window) {
    history.scrollRestoration = 'manual';
}

document.addEventListener("DOMContentLoaded", () => {
    const scrollKey = "scrollPos_" + window.location.pathname;
    const savedScrollPos = sessionStorage.getItem(scrollKey);

    if (savedScrollPos) {
        requestAnimationFrame(() => {
            window.scrollTo(0, parseInt(savedScrollPos, 10));
            sessionStorage.removeItem(scrollKey);
        });
    }

    document.addEventListener("submit", () => {
        sessionStorage.setItem(scrollKey, window.scrollY);
    });

    Object.keys(sessionStorage).forEach(key => {
        if (key.startsWith("scrollPos_") && key !== scrollKey) {
            sessionStorage.removeItem(key);
        }
    });
});